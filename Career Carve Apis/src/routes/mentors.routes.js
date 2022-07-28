const express = require('express');
const router = express.Router();

const mentorsController = require('../controllers/mentors.controller');

router.post('/add', mentorsController.addMentors);

router.get('/schedule/suggestion/:day/:area_of_intrest', mentorsController.schedule);


module.exports = router;