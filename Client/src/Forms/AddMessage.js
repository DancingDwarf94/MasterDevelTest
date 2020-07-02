import React, { useState } from 'react';
import { Snackbar, Grid } from '@material-ui/core'
import { Alert } from '@material-ui/lab'
import { Sign } from '../Utils/HashUtil';

export default function AddMessage() {
    const [snackbar, setSnackbar] = useState();
    const [open, setOpen] = useState(true);

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }
        setSnackbar();
        setOpen(false);
    };
    

    const submit = (e) => {
        e.preventDefault();
        setSnackbar();
        setOpen(true);
        var object = {};
        
        object.msg = document.getElementById('msg').value;
        object.tags = document.getElementById('tags').value.split(',');

        const body = JSON.stringify(object);
        const route = 'http://localhost:8080/message';

        const requestOptions = {
            method: 'POST',
            body,
            headers: {
                'Content-Type': 'application/json',
                'x-Key': document.getElementById('key').value,
                'x-Signature': Sign(object, route),
                'x-Route': route,
                'x-Body': body
            }
        }

        fetch(route, requestOptions)
            .then(response => response)
            .then(function (data) {
                if (data.ok) {
                    setSnackbar(
                        <Snackbar
                            anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
                            open={open} autoHideDuration={6000} onClose={handleClose}>
                            <Alert variant='filled' onClose={handleClose} severity="success">
                                Message added successfully
                            </Alert>
                        </Snackbar>
                    )
                } else {
                    setSnackbar(
                        <Snackbar
                            anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
                            open={open} autoHideDuration={6000} onClose={handleClose}>
                            <Alert variant='filled' onClose={handleClose} severity="error">
                                {data.status + ' ' + data.statusText}
                            </Alert>
                        </Snackbar>
                    )
                }
            });
    }

    return (
        <React.Fragment>
            {snackbar}
            <form onSubmit={(e) => submit(e)} id='credentials'>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <label>Msg</label>
                    </Grid>
                    <Grid item xs={12}>
                        <input name='msg' id='msg' required />
                    </Grid>
                    <Grid item xs={12}>
                        <label>Tags (separated by comma)</label>
                    </Grid>
                    <Grid item xs={12}>
                        <input name='tags' id='tags' required />
                    </Grid>
                    <Grid item xs={12}>
                        <button type='submit'>Add Message</button>
                    </Grid>
                </Grid>
            </form>
        </React.Fragment>
    );
}