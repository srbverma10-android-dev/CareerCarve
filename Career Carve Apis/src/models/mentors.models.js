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

Mentors.searchByMentorId = (id, result) => {
    dbConn.query('Select * From mentors WHERE mentor_id = ?', id, (err, res) => {
        if(err){
            console.log('Error while scheduling a meeting', err)
            result(null, err);            
        } else {
            result(null, res)
        }
    })
}

Mentors.scheduleMeeting = (day, area_of_interst, result) => {

    // "Sunday" -> day = 0
    // "Monday" -> day = 1
    // "Tuesday" -> day = 2
    // "Wednesday" -> day = 3
    // "Thursday" -> day = 4
    // "Friday" -> day = 5
    // "Saturday" -> day = 6

    var dayInStr = ""
    if(day == '0')
        dayInStr = "sun";
    else if (day == '1')
        dayInStr = "mon";
    else if (day == '2')
        dayInStr = "tue";
    else if (day == '3')
        dayInStr = "wed";
    else if (day == '4')
        dayInStr = "thrus";
    else if (day == '5')
        dayInStr = "fri";
    else if (day == '6')
        dayInStr = "sat";



    var query = 'SELECT * FROM mentors WHERE area_of_interst=? AND avail_' + dayInStr + '=1 LIMIT 1'
    console.log(query)
    dbConn.query(query, area_of_interst, (err, res) => {
        if(err){
            console.log('Error while scheduling a meeting', err)
            result(null, err);            
        } else {
            result(null, res)
        }
    })
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