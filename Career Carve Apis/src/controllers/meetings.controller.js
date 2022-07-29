const Meetings = require('../models/meetings.models')


exports.getAllMeetingsByStudentId = (req, res) => {
    console.log("Fetching Meetings...");
    Meetings.getAllMeetingsByStudentId(req.params.student_id, (err, meeting)=>{
        if(err)
        res.send(err);
        res.json({status: true, data: meeting})
    })
}

exports.getAllMeetingsByMentorId = (req, res) => {
    console.log("Fetching Meetings...");
    Meetings.getAllMeetingsByMentorId(req.params.mentor_id, (err, meeting)=>{
        if(err)
        res.send(err);
        res.json({status: true, data: meeting})
    })
}


exports.addMeeting = (req, res) => {
    const MeetingReqData = new Meetings(req.body);
    console.log("Adding Meetings...", MeetingReqData);
    if(req.body.constructor === Object && Object.keys(req.body).length === 0){
        res.send(400).send({success : false, message : "Please fill all fields"});
    } else {
        console.log('valid data');
        Meetings.addMeeting(MeetingReqData, (err, meeting)=>{
            if(err)
            res.send(err);
            res.json({status: true, data: meeting.insertId})
        })
    }
}