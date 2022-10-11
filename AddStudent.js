import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

class AddStudent extends Component {
    constructor(props) {
        super(props);
        this.state = {studentname:'', studentemail:''};
    };

    handleClickOpen = () => {
        this.setState( {open:true} );
    };

    handleClose = () => {
        this.setState( {open:false} );
    };

    handleChangeName = (event) => {
        this.setState({studentemail: event.target.value});
    }

    handleChangeEmail = (event) => {
        this.setState({studentemail: event.target.value});
    }

    handleAdd = () => {
        this.props.addStudent(this.state.studentname, this.state.studentemail);
        this.handleClose();
    }

    render()  { 
        return (
            <div>
                <Button variant="outlined" color="primary" style={{margin: 10}} onClick={this.handleClickOpen}>
                Add Student
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose}>
                    <DialogTitle>Add Student</DialogTitle>

                    <DialogContent  style={{paddingTop: 20}} >
                     <TextField autoFocus fullWidth label="Name" name="name" onChange={this.handleChangeName}  /> 
                     <TextField autoFocus fullWidth label="Email" name="email" onChange={this.handleChangeEmail}  /> 
                    </DialogContent>

                    <DialogActions>
                     <Button color="secondary" onClick={this.handleClose}>Cancel</Button>
                     <Button id="Add" color="primary" onClick={this.handleAdd}>Add</Button>
                    </DialogActions>
                </Dialog>      
            </div>
      ); 
    }    
}

// required property:  addCourse is a function to call to perform the Add action
AddStudent.propTypes = {
  addstudent : PropTypes.func.isRequired
}

export default AddStudent;
