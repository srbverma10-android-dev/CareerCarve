var dbConn = require('../../config/db.config');

var Students = function(students){
    this.student_name   = students.student_name;
    this.student_email  = students.student_email;
}

Students.addStudents = (studentReqData, result) => {
    dbConn.query('Insert into students SET ? ', studentReqData, (err, res)=>{
        if(err){
            console.log('Error while inserting data...');
            result(null, {status : false, message : err});
        } else {
            console.log('Student created successfully');
            result(null, res)
        }
    })
}

module.exports = Students