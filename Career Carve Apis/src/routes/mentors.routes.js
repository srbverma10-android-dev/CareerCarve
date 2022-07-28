const express = require('express');
const router = express.Router();

const mentorsController = require('../controllers/mentors.controller');
const studentsController = require('../controllers/students.controller')

router.post('/add', mentorsController.addMentors);

router.get('/schedule/suggestion/:day/:area_of_intrest', mentorsController.schedule);

router.get('/searchById/:mentor_id', mentorsController.searchByMentorId);

module.exports = router;