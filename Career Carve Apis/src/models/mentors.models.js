var dbConn = require('../../config/db.config');

var Mentors = function(mentors){
    this.mentor_name               = mentors.mentor_name;
    this.mentor_email              = mentors.mentor_email;
    this.area_of_interst           = mentors.area_of_interst;
    this.avail_mon                 = mentors.avail_mon;
    this.avail_tue                 = mentors.avail_tue;
    this.avail_wed                 = mentors.avail_wed;
    this.avail_thrus               = mentors.avail_thrus;
    this.avail_fri                 = mentors.avail_fri;
    this.avail_sat                 = mentors.avail_sat;
    this.avail_sun                 = mentors.avail_sun;
    this.schedule_start            = mentors.schedule_start;
    this.schedule_end              = mentors.schedule_end;
}

Mentors.addMentor = (mentorReqData, result) => {
    dbConn.query('Insert into mentors SET ? ', mentorReqData, (err, res)=>{
        if(err){
            console.log('Error while inserting data...');
            result(null, {status : false, message : err});
        } else {
            console.log('mentor created successfully');
            result(null, res)
        }
    })
}

module.exports = Mentors