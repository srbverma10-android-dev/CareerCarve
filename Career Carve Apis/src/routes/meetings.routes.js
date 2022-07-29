const express = require('express');
const router = express.Router();

const meetingsController = require('../controllers/meetings.controller')

router.post('/add', meetingsController.addMeeting);

router.get('/getAllById/student/:student_id', meetingsController.getAllMeetingsByStudentId);

router.get('/getAllById/mentor/:mentor_id', meetingsController.getAllMeetingsByMentorId);

module.exports = router;