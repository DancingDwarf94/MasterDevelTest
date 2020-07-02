import React, { useState } from 'react';
import { Snackbar, Grid } from '@material-ui/core'
import { Alert } from '@material-ui/lab'
import { Sign } from '../Utils/HashUtil';

export default function GetMessages() {
    const [snackbar, setSnackbar] = useState();
    const [open, setOpen] = useState(true);
    const [messages, setMessages] = useState([])

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
        object.tag = document.getElementById('tag').value;

        const body = JSON.stringify(object);
        const route = 'http://localhost:8080/messages/' + object.tag;

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
                if (data.message && data.message === 'Message retreived') {
                    setMessages(data.dataObject)

                    setSnackbar(
                        <Snackbar
                            anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
                            open={open} autoHideDuration={6000} onClose={handleClose}>
                            <Alert variant='filled' onClose={handleClose} severity="success">
                                Messages retreived successfully
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

    const printMessages = () => {
        return (
            <Grid container>
                {messages.map(message => {
                    return (<Grid item xs={12}>
                        msg: {message.msg}
                    </Grid>)
                })
                }
            </Grid>
        )
    }

    return (
        <React.Fragment>
            {snackbar}
            <form onSubmit={(e) => submit(e)} tag='credentials'>
                <Grid container spacing={2}>
                    <Grid container item xs={6}>
                        <Grid item xs={12}>
                            <label>Msg tag</label>
                        </Grid>
                        <Grid item xs={12}>
                            <input name='tag' id='tag' required />
                        </Grid>
                        <Grid item xs={12}>
                            <button type='submit'>Get Message</button>
                        </Grid>
                    </Grid>
                    <Grid container item xs={6}>
                        {
                            printMessages()
                        }
                    </Grid>
                </Grid>
            </form>
        </React.Fragment>
    );
}