const Mentors = require('../models/mentors.models')

exports.addMentors = (req, res) => {
    const mentorReqData = new Mentors(req.body);
    console.log("Adding Mentors...", mentorReqData);
    if(req.body.constructor === Object && Object.keys(req.body).length === 0){
        res.send(400).send({success : false, message : "Please fill all fields"});
    } else {
        console.log('valid data');
        Mentors.addMentor(mentorReqData, (err, mentor)=>{
            if(err)
            res.send(err);
            res.json({status: true, data: mentor})
        })
    }
}