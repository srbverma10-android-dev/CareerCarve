const express = require('express');
const router = express.Router();

const meetingsController = require('../controllers/meetings.controller')

router.post('/add', meetingsController.addMeeting);

module.exports = router;