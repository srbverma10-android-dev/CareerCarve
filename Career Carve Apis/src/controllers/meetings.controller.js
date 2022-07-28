const Meetings = require('../models/meetings.models')

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