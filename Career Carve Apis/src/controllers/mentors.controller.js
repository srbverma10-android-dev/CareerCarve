const Mentors = require('../models/mentors.models')


exports.schedule = (req, res) => {
    console.log("Scheduling...", req.body);
    Mentors.scheduleMeeting(req.params.day, req.params.area_of_intrest, (err, mentor) => {
        if(err)
            res.send(err);
        res.json({status:true, mentorDetails:mentor[0]})
    })
}


exports.searchByMentorId = (req, res) => {
    console.log("Searching by mentor ID");
    Mentors.searchByMentorId(req.params.mentor_id, (err, mentor) => {
        if(err)
            res.send(err);
        res.json({status : true, mentorDetails:mentor[0]})
    })
}


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
            res.json({status: true, data: mentor.insertId})
        })
    }
}