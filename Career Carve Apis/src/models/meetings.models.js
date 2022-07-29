var dbConn = require('../../config/db.config');

var Meeting = function(meeting){
    this.student_id = meeting.student_id;
    this.mentor_id = meeting.mentor_id;
    this.student_email = meeting.student_email;
    this.mentor_email = meeting.mentor_email;
    this.student_name = meeting.student_name;
    this.mentor_name = meeting.mentor_name;
    this.schedule_start = meeting.schedule_start;
    this.schedule_end = meeting.schedule_end;
    this.duration = meeting.duration;
    this.meeting_date = meeting.meeting_date;
}

Meeting.getAllMeetingsByMentorId = (id, result) => {
    var query = 'SELECT * FROM meetings WHERE mentor_id = ' + id
    dbConn.query(query , (err, res)=>{
        if(err){
            console.log('Error while inserting data...');
            result(null, {status : false, message : err});
        } else {
            console.log('mentor created successfully');
            result(null, res)
        }
    })
}


Meeting.getAllMeetingsByStudentId = (id, result) => {
    var query = 'SELECT * FROM meetings WHERE student_id = ' + id
    dbConn.query(query , (err, res)=>{
        if(err){
            console.log('Error while inserting data...');
            result(null, {status : false, message : err});
        } else {
            console.log('mentor created successfully');
            result(null, res)
        }
    })
}

Meeting.addMeeting = (meetingReqData, result) => {
    // var queryForFindingStartTimming = 'Select schedule_end From meetings Where mentor_id = ' + meetingReqData.mentor_id + ' AND meeting_date = Date(' + meetingReqData.meeting_date +') ORDER BY schedule_end DESC LIMIT 1';
    var queryForFindingStartTimming = 'Select schedule_end From meetings Where mentor_id = ' + meetingReqData.mentor_id + ' AND meeting_date = (Date \'' + meetingReqData.meeting_date +'\') ORDER BY schedule_end DESC LIMIT 1';
    console.log(queryForFindingStartTimming);
    dbConn.query(queryForFindingStartTimming,(err, res)=>{
        if(err){
            console.log('Error while inserting data...', err);
            result(null, {status : false, message : err});
        } else {
            console.log('mentor created successfully');
            console.log(res);            
            if(res.length > 0){    
                meetingReqData.schedule_start = res[0].schedule_end;

                var now = new Date();
                var splitStr = res[0].schedule_end.split(":");
    
                now.setHours(splitStr[0]);
                now.setMinutes(splitStr[1]);
                now.setSeconds(splitStr[2]);
    
                if(meetingReqData.duration === '30'){
                    now.addMinutes(30);
                } else if (meetingReqData.duration === '45'){
                    now.addMinutes(45);
                } else if (meetingReqData.duration === '60'){
                    now.addMinutes(60);
                }
    
                var splitEnd = meetingReqData.schedule_end.split(':');
                if(now.getHours() < splitEnd[0] || (now.getHours() == splitEnd[0] && now.getMinutes() < splitEnd[1])){
    
                    var endTime = now.getHours().toString().length < 2 ? '0' + now.getHours().toString() : now.getHours().toString().concat(':', 
                    now.getMinutes().toString().length < 2 ? '0' + now.getMinutes().toString() : now.getMinutes().toString()).concat(':', 
                    now.getSeconds().toString().length < 2 ? '0' + now.getSeconds().toString() : now.getSeconds().toString());
            
                    meetingReqData.schedule_end = endTime;
                
                    dbConn.query('Insert into meetings SET ? ', meetingReqData, (err, res)=>{
                        if(err){
                            console.log('Error while inserting data...');
                            result(null, {status : false, message : err});
                        } else {
                            console.log('mentor created successfully');
                            result(null, res)
                        }
                    })
    
                } else {
                    result(null, {status : false, message : "schedule_end"});
                }
            } else {            
                var now = new Date();

                var splitStr = meetingReqData.schedule_start.split(":");

                now.setHours(splitStr[0]);
                now.setMinutes(splitStr[1]);
                now.setSeconds(splitStr[2]);

                if(meetingReqData.duration === '30'){
                    now.addMinutes(30);
                } else if (meetingReqData.duration === '45'){
                    now.addMinutes(45);
                } else if (meetingReqData.duration === '60'){
                    now.addMinutes(60);
                }

                var splitEnd = meetingReqData.schedule_end.split(':');

                if(now.getHours() < splitEnd[0] || (now.getHours() == splitEnd[0] && now.getMinutes() < splitEnd[1])){
    
                    var endTime = now.getHours().toString().length < 2 ? '0' + now.getHours().toString() : now.getHours().toString().concat(':', 
                    now.getMinutes().toString().length < 2 ? '0' + now.getMinutes().toString() : now.getMinutes().toString()).concat(':', 
                    now.getSeconds().toString().length < 2 ? '0' + now.getSeconds().toString() : now.getSeconds().toString());
                            
                    meetingReqData.schedule_end = endTime;
                
                    dbConn.query('Insert into meetings SET ? ', meetingReqData, (err, res)=>{
                        if(err){
                            console.log('Error while inserting data...');
                            result(null, {status : false, message : err});
                        } else {
                            console.log('mentor created successfully');
                            result(null, res)
                        }
                    })
    
                } else {
                    result(null, {status : false, message : "schedule_end"});
                }
            }

        }
    })
}

Date.prototype.addHours = function(hours) {
    this.setHours(this.getHours() + hours);
    return this;
  };

Date.prototype.addMinutes = function(minutes) {
    this.setMinutes(this.getMinutes() + minutes);
    return this;
  };

module.exports = Meeting





