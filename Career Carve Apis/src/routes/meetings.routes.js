const express = require('express');
const router = express.Router();

const meetingsController = require('../controllers/meetings.controller')

router.post('/add', meetingsController.addMeeting);

router.get('/getAllById/student/:student_id', meetingsController.getAllMeetingsByStudentId);

module.exports = router;