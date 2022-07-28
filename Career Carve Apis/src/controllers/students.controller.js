const Students = require('../models/students.models')

exports.addStudents = (req, res) => {
    const studentReqData = new Students(req.body);
    console.log("Adding Students...", studentReqData);
    if(req.body.constructor === Object && Object.keys(req.body).length === 0){
        res.send(400).send({success : false, message : "Please fill all fields"});
    } else {
        console.log('valid data');
        Students.addStudents(studentReqData, (err, student)=>{
            if(err)
            res.send(err);
            res.json({status: true, data: student.insertId})
        })
    }
}