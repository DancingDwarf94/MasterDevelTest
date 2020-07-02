import React, { useState } from 'react';
import { Snackbar, Grid } from '@material-ui/core'
import { Alert } from '@material-ui/lab'

export default function CredentialsContainer() {
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
        const form = document.getElementById('credentials');
        const formData = new FormData(form);

        var object = {};
        formData.forEach(function (value, key) {
            object[key] = value;
        });

        const requestOptions = {
            method: 'PUT',
            body: JSON.stringify(object),
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch('http://localhost:8080/credential', requestOptions)
            .then(response => response)
            .then(function (data) {
                if (data.ok) {
                    setSnackbar(
                        <Snackbar
                            anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
                            open={open} autoHideDuration={6000} onClose={handleClose}>
                            <Alert variant='filled' onClose={handleClose} severity="success">
                                Credentials added successfully
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
            {snackbar ? snackbar : null}
            <form onSubmit={(e) => submit(e)} id='credentials'>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <label>Key</label>
                    </Grid>
                    <Grid item xs={12}>
                        <input name='key' id='key' required />
                    </Grid>
                    <Grid item xs={12}>
                        <label>Shared Secret</label>
                    </Grid>
                    <Grid item xs={12}>
                        <input name='shared_secret' id='shared_secret' required />
                    </Grid>
                    <Grid item xs={12}>
                        <button type='submit'>Add Credentials</button>
                    </Grid>
                </Grid>
            </form>
        </React.Fragment>
    );
}