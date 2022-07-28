const mysql = require('mysql2')

const dbConn = mysql.createConnection({
    host : 'localhost',
    user : 'parsa', 
    password : 'your_password', 
    database : 'career_carve_database'
});

dbConn.connect(function(error){
    if(error) throw error;
    console.log('Database Connected Successfully');
})

module.exports = dbConn;
