const express = require('express');
const bodyParser = require('body-parser');
const app = express();

const port = process.env.PORT || 8000;

app.use(bodyParser.urlencoded({extended: false}));

app.use(bodyParser.json());

app.get('/', (req, res) => {
    res.send('Hello World');
});

const mentorRoutes = require('./src/routes/mentors.routes')
const studentRoutes = require('./src/routes/students.routes')
const meetingsRoutes = require('./src/routes/meetings.routes')

app.use('/api/v1/mentors', mentorRoutes);
app.use('/api/v1/students', studentRoutes);
app.use('/api/v1/meetings', meetingsRoutes);

app.listen(port, ()=>{
    console.log(`Express server is running at port ${port}`);
})