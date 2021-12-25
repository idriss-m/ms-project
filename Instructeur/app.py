import os
from flask import Flask, request, jsonify, make_response
from flask_sqlalchemy import SQLAlchemy
from marshmallow import fields
from marshmallow_sqlalchemy import SQLAlchemyAutoSchema 
import py_eureka_client.eureka_client as eureka_client
from spring_config import ClientConfigurationBuilder
from spring_config.client import SpringConfigClient

## environment variables
config_server = os.environ['CONFIG_SERVER']
eureka_server = os.environ['EUREKA_SERVER']
mysql_user = os.environ['MYSQL_USER']
mysql_passwd = os.environ['MYSQL_PASSWD']
mysql_server = os.environ['MYSQL_SERVER']

## Eureka Client configuration 
server_port = 5000
# The flowing code will register the ms to eureka server and also start to send heartbeat every 30 seconds
eureka_client.init(eureka_server=eureka_server,
                   app_name="instructeur-service",
                   instance_port=5000)

## Config client configuration
config = (
    ClientConfigurationBuilder()
    .app_name("instructeur-ms")
    .address(config_server)
    .build()
)
c = SpringConfigClient(config)
c.get_config()
 
## Connection to DB
db = SQLAlchemy()

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://{}:{}@{}/instructeur'.format(mysql_user, mysql_passwd, mysql_server)
#app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://' + mysql_user + ':' + mysql_passwd + '@' + mysql_server + '/instructeur'
db = SQLAlchemy(app)

## Model
class Instructeur(db.Model):
    __tablename__ = 'instructeur'
 
    id = db.Column(db.Integer, primary_key=True)
    nom = db.Column(db.String(80))
    prenom =  db.Column(db.String(80))
    cin = db.Column(db.Integer)
    def __init__(self, nom, prenom, cin):
        self.nom = nom
        self.prenom = prenom
        self.cin = cin 
    def create(self):
        db.session.add(self)
        db.session.commit()
        return self
    def __repr__(self):
        return f"{self.id}"

db.create_all()

class InstructeurSchema(SQLAlchemyAutoSchema):
   class Meta():
       model = Instructeur
       include_relationships = True
       load_instance = True
       sqla_session = db.session
   id = fields.Number(dump_only=True)
   nom = fields.String(required=True)
   prenom = fields.String(required=True)
   cin = fields.Number(required=True)

@app.route('/api/instructeur/get-all-instructeur', methods=['GET'])
def index():
    get_instructeurs = Instructeur.query.all()
    instructeur_schema = InstructeurSchema(many=True)
    instructeurs = instructeur_schema.dump(get_instructeurs)
    return make_response(jsonify({"instrcuteur": instructeurs}))
 
@app.route('/api/get-instructeur/<id>', methods=['GET'])
def get_instructeur_by_id(id):
    get_ins = Instructeur.query.get(id)
    ins_schema = InstructeurSchema()
    ins = ins_schema.dump(get_ins)
    return make_response(jsonify({"Instructeur": ins}))

@app.route('/api/instructeur/edit-instructeur/<id>', methods=['PUT'])
def update_instructeur_by_id(id):
    data = request.get_json()
    get_ins = Instructeur.query.get(id)
    if data.get('nom'):
        get_ins.nom = data['nom']
    if data.get('prenom'):
        get_ins.prenom = data['prenom']
    if data.get('cin'):
        get_ins.cin = data['cin']
    db.session.add(get_ins)
    db.session.commit()
    ins_schema = InstructeurSchema(only=['id', 'nom', 'prenom', 'cin'])
    ins = ins_schema.dump(get_ins)
    return make_response(jsonify({"Instructeur": ins}))

@app.route('/api/instructeur/delete-instructeur/<id>', methods=['DELETE'])
def delete_instructeur_by_id(id):
    get_ins = Instructeur.query.get(id)
    db.session.delete(get_ins)
    db.session.commit()
    return make_response("Instructeur deleted", 204)

@app.route('/api/instructeur/create-instructeur', methods=['POST'])
def create_instructeur():
    data = request.get_json()
    ins_schema = InstructeurSchema()
    ins = ins_schema.load(data)
    result = ins_schema.dump(ins)
    db.session.add(ins)
    db.session.commit()
    return make_response(jsonify({"Instructeur": result}), 200)

if __name__ == "__main__":
    app.run(debug=True)
