class UsersModel:

    def __init__(self, connection):
        self.connection = connection

    def init_table(self):
        cursor = self.connection.cursor()
        cursor.execute('''CREATE TABLE IF NOT EXISTS users
                                    (id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     username VARCHAR(50),
                                     password_hash VARCHAR(128),
                                     email VARCHAR(128),
                                     wood INTEGER,
                                     iron INTEGER,
                                     metal INTEGER,
                                     stone INTEGER,
                                     obtain_res VARCHAR(6),
                                     give_res VARCHAR(6),
                                     obtain_num INTEGER,
                                     give_num INTEGER,
                                     tawnhall INTEGER,
                                     barraks INTEGER,
                                     shack INTEGER,
                                     farm INTEGER,
                                     house INTEGER,
                                     warehouse INTEGER,
                                     big_house INTEGER,
                                     church INTEGER)''')
        cursor.close()
        self.connection.commit()

    def insert_newuser(self, username, password, email, wood, iron, metal, stone,
                      obtain_res, give_res, obtain_num, give_num):
        cursor = self.connection.cursor()
        cursor.execute('''INSERT INTO users
                          (username, password_hash, email, wood, iron, metal, stone, obtain_res,
                           give_res, obtain_num, give_num, tawnhall, barraks, shack, farm, house,
                                     warehouse, big_house, church)
                          VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)''', (username, password, email,
                                                      wood, iron, metal, stone,
                                                      obtain_res, give_res, obtain_num, give_num, 0, 0, 0, 0, 0, 0, 0, 0))
        cursor.close()
        self.connection.commit()

    def update_resource(self, resource, amount, id):
        cursor = self.connection.cursor()
        if resource == 'wood':
            cursor.execute('''UPDATE users SET wood = ? WHERE id = ?''',
                           (str(amount), str(id),))
        elif resource == 'iron':
            cursor.execute('''UPDATE users SET iron = ? WHERE id = ?''',
                           (str(amount), str(id),))
        elif resource == 'metal':
            cursor.execute('''UPDATE users SET metal = ? WHERE id = ?''',
                           (str(amount), str(id),))
        elif resource == 'stone':
            cursor.execute('''UPDATE users SET stone = ? WHERE id = ?''',
                           (str(amount), str(id),))
        cursor.fetchone()

    def update_building(self, building, user_id):
        cursor = self.connection.cursor()
        if building == 'tawnhall':
            cursor.execute('''UPDATE users SET tawnhall = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'barraks':
            cursor.execute('''UPDATE users SET barraks = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'shack':
            cursor.execute('''UPDATE users SET shack = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'farm':
            cursor.execute('''UPDATE users SET farm = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'house':
            cursor.execute('''UPDATE users SET house = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'warehouse':
            cursor.execute('''UPDATE users SET warehouse = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'big_house':
            cursor.execute('''UPDATE users SET big_house = 1 WHERE id = ?''', (str(user_id),))
        elif building == 'church':
            cursor.execute('''UPDATE users SET church = 1 WHERE id = ?''', (str(user_id),))
        cursor.fetchone()

    def get_buildings(self, user_id):
        cursor = self.connection.cursor()
        cursor.execute('''SELECT tawnhall, barraks, shack, farm, house, warehouse, big_house, church
         FROM users WHERE id = ?''', (str(user_id),))
        row = cursor.fetchone()
        return row

    def get_resource(self, resource, user_id):
        cursor = self.connection.cursor()
        if resource == 'wood':
            cursor.execute('''SELECT wood FROM users WHERE id = ?''', (str(user_id),))
        elif resource == 'iron':
            cursor.execute('''SELECT iron FROM users WHERE id = ?''', (str(user_id),))
        elif resource == 'metal':
            cursor.execute('''SELECT metal FROM users WHERE id = ?''', (str(user_id),))
        elif resource == 'stone':
            cursor.execute('''SELECT stone FROM users WHERE id = ?''', (str(user_id),))
        row = cursor.fetchone()
        return row

    def get_all(self, user_id):
        cursor = self.connection.cursor()
        cursor.execute('''SELECT wood, iron, metal, stone FROM users WHERE id = ?''', (str(user_id),))
        row = cursor.fetchone()
        return row

    def get_trades(self):
        cursor = self.connection.cursor()
        cursor.execute('''SELECT id, obtain_res, obtain_num, give_res, give_num
                        FROM users WHERE obtain_num > 0''')
        row = cursor.fetchone()
        return row

    def set_trade(self, id, obtain_res, give_res, obtain_num, give_num):
        cursor = self.connection.cursor()
        cursor.execute('''UPDATE users SET obtain_res = ? WHERE id = ?''',
                       (str(obtain_res), str(id),))
        cursor.execute('''UPDATE users SET give_res = ? WHERE id = ?''',
                       (str(give_res), str(id),))
        cursor.execute('''UPDATE users SET obtain_num = ? WHERE id = ?''',
                       (str(obtain_num), str(id),))
        cursor.execute('''UPDATE users SET give_num = ? WHERE id = ?''',
                       (str(give_num), str(id),))
        cursor.fetchone()

    def get_id(self, user_name, password_hash):
        cursor = self.connection.cursor()
        cursor.execute("SELECT id FROM users WHERE username = ? AND password_hash = ?",
                       (user_name, password_hash))
        row = cursor.fetchone()
        return row

    def exists(self, username, password_hash=None):
        cursor = self.connection.cursor()
        if password_hash is not None:
            cursor.execute("SELECT * FROM users WHERE username = ? and password_hash = ?",
                           (username, password_hash))
        else:
            cursor.execute("SELECT * FROM users WHERE username = ?", (str(username),))
        row = cursor.fetchone()
        return (True, row[0]) if row else (False,)
