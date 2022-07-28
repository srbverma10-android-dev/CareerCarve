const express = require('express');
const router = express.Router();

const studentsController = require('../controllers/students.controller')

router.post('/add', studentsController.addStudents);

module.exports = router;