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
def login(username, password, email):
    user_model.insert_newuser(username, password, email, 0, 0, 0, 0, "", "", 0, 0)
    exists = user_model.exists(username, password)
    if not exists[0]:
        session['username'] = username
        session['user_id'] = exists[1]


@app.route('/auth', methods=["GET"])
def auth(username, password):
    exists = user_model.exists(username, password)
    if exists[0]:
        session['username'] = username
        session['user_id'] = exists[1]


@app.route('/resources', methods=["GET"])
def get_all_resources():
    # res = list(user_model.get_all(session['user_id']))
    snippet = {'wood': 0, 'iron': 0, 'metal': 0, 'stone': 0}
    # i = 0
    # for el in snippet:
    #     snippet[el] = res[i]
    #     i += 1
    return jsonify(snippet)


@app.route('/change_res', methods=["POST"])
def change_resource(res, num):
    user_model.update_resource(res, num, session['user_id'])


@app.route('/get_res', methods=["GET"])
def get_resource(res):
    reses = list(user_model.get_resource(res, session['user_id']))
    return make_response(jsonify({reses[0]: reses[1]}))


@app.route('/set_trade', methods=["POST"])
def set_trade(obtain_res, give_res, obtain_num, give_num):
    user_model.set_trade(session['user_id'], obtain_res, give_res, obtain_num, give_num)


@app.route('/get_trades', methods=["GET"])
def get_trades():
    trades = list(user_model.get_trades())
    snippet = {}
    i = 0
    trades_u = []
    k, j = 0, 5
    for _ in range(len(trades)//5):
        trades_u.append(trades[k:j])
        k += 5
        j += 5

    prep = {}
    for row in range(len(trades_u)):
        prep['obtain_res'] = trades_u[row][1]
        prep['give_res'] = trades_u[row][2]
        prep['obtain_num'] = trades_u[row][3]
        prep['give_num'] = trades_u[row][4]
        snippet[trades_u[0]] = prep
        prep = {}

    return make_response(jsonify(snippet))


@app.route('/logout')
def logout():
    session.pop('username', 0)
    session.pop('user_id', 0)
    return redirect('/sign_in')


if __name__ == '__main__':
    app.run(port=8080, host='127.0.0.1')
