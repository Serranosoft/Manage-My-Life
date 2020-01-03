use mysql;
update user set authentication_string=password('123456') where user='root';
update user set plugin="mysql_native_password" where User='root';
flush privileges;
