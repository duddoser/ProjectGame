from flask import Flask, redirect, session, request, make_response, jsonify
from db import DB
from usersmodel import UsersModel

app = Flask(__name__)
app.config['SECRET_KEY'] = 'android_game_secret_key'

OPERATIONS = {}
u_db = DB('users.db')
UsersModel(u_db.get_connection()).init_table()
user_model = UsersModel(u_db.get_connection())


@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)


@app.route('/login', methods=["POST"])
def login():
    result = request.get_json()
    exists = user_model.exists(result['username'])
    if not exists[0]:
        user_model.insert_newuser(result['username'], result['password'], result['email'],
                              100, 100, 100, 100, "", "", 0, 0)
        return jsonify({"message": "success"})
    return jsonify({"message": "none"})


@app.route('/auth', methods=["POST"])
def auth():
    result = request.get_json()
    exists = user_model.exists(result['username'], result['password'])
    if exists[0]:
        return jsonify({"message": "success"})
    return jsonify({"message": "none"})



@app.route('/resources', methods=["POST"])
def get_all_resources():
    result = request.get_json()
    res = list(user_model.get_all(result['user_id']))
    snippet = {'wood': 0, 'iron': 0, 'metal': 0, 'stone': 0}
    i = 0
    for el in snippet:
        snippet[el] = res[i]
        i += 1
    return jsonify(snippet)


@app.route('/change_res', methods=["POST"])
def change_resource():
    result = request.get_json()
    user_model.update_resource(result['resource'], result['amount'], result['user_id'])


@app.route('/get_res', methods=["GET"])
def get_resource(res):
    reses = list(user_model.get_resource(res, session['user_id']))
    return make_response(jsonify({reses[0]: reses[1]}))


@app.route('/get_buildings', methods=['POST'])
def get_buildings():
    result = request.get_json()
    buildings = list(user_model.get_buildings(result['user_id']))
    snippet = {'tawnhall': 0, 'barraks': 0, 'shack': 0, 'farm': 0, 'house': 0, 'warehouse': 0,
               'big_house': 0, 'church': 0}
    i = 0
    for b in snippet:
        snippet[b] = buildings[i]
        i += 1
    return jsonify(snippet)


@app.route('/set_buildings', methods=['POST'])
def set_buildings():
    result = request.get_json()
    user_model.update_building(result['building'], result['user_id'])
    return jsonify({"message": "success"})


@app.route('/set_trade', methods=["POST"])
def set_trade():
    result = request.get_json()
    user_model.set_trade(result['user_id'], result['obtain_res'], result['give_res'], result['obtainInt'], result['giveInt'])
    return jsonify({"message": "success"})


@app.route('/get_trades', methods=["POST"])
def get_trades():
    trades = list(user_model.get_trades())
    # trades_u = []
    # k, j = 0, 5
    # for _ in range(len(trades)//5):
    #     trades_u.append(trades[k:j])
    #     k += 5
    #     j += 5

    # data = []
    # prep = {}
    # for row in range(len(trades_u)):
    #     prep['user_id'] = trades_u[row][0]
    #     prep['obtain_res'] = trades_u[row][1]
    #     prep['give_res'] = trades_u[row][2]
    #     prep['obtainInt'] = trades_u[row][3]
    #     prep['giveInt'] = trades_u[row][4]
    #     data.append(prep)
    #     prep = {}

    return jsonify(trades)


@app.route('/get_id', methods=["POST"])
def get_id():
    result = request.get_json()
    user_id = list(user_model.get_id(result['username'], result['password']))
    return jsonify({'user_id': user_id[0]})


if __name__ == '__main__':
    app.run()
