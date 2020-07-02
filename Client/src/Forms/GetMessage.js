import React, { useState } from 'react';
import { Snackbar, Grid } from '@material-ui/core'
import { Alert } from '@material-ui/lab'
import { Sign } from '../Utils/HashUtil';

export default function GetMessage() {
    const [snackbar, setSnackbar] = useState();
    const [open, setOpen] = useState(true);
    const [message, setMessage] = useState({})

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

        let object = {};
        object.id = document.getElementById('id').value;

        const body = JSON.stringify(object);
        const route = 'http://localhost:8080/message/' + object.id;

        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'x-Key': document.getElementById('key').value,
                'x-Signature': Sign(object, route),
                'x-Route': route
            }
        }

        fetch(route, requestOptions)
            .then(response => response.json())
            .then(function (data) {
                if (data.dataObject && data.dataObject.id) {
                    setMessage(data.dataObject)

                    setSnackbar(
                        <Snackbar
                            anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
                            open={open} autoHideDuration={6000} onClose={handleClose}>
                            <Alert variant='filled' onClose={handleClose} severity="success">
                                Message retreived successfully
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

    const printMessage = () => {
        return (
            <Grid container>
                <Grid item xs={12}>
                    msg: {message.msg}
                </Grid>
                <Grid item xs={12}>
                    tags: {
                        message.tags ?
                            message.tags.toString() : null
                    }
                </Grid>
            </Grid>
        )
    }

    return (
        <React.Fragment>
            {snackbar}
            <form onSubmit={(e) => submit(e)} id='credentials'>
                <Grid container spacing={2}>
                    <Grid container item xs={6}>
                        <Grid item xs={12}>
                            <label>Msg id</label>
                        </Grid>
                        <Grid item xs={12}>
                            <input name='id' id='id' required />
                        </Grid>
                        <Grid item xs={12}>
                            <button type='submit'>Get Message</button>
                        </Grid>
                    </Grid>
                    <Grid container item xs={6}>
                        {
                            printMessage()
                        }
                    </Grid>
                </Grid>
            </form>
        </React.Fragment>
    );
}