const express = require('express');
const router = express.Router();

const mentorsController = require('../controllers/mentors.controller');

router.post('/add', mentorsController.addMentors);

module.exports = router;