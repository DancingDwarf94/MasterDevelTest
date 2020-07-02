import React from 'react';
import Crendentials from './Forms/Credentials';
import { Typography } from '@material-ui/core';
import AddMessage from './Forms/AddMessage';
import GetMessage from './Forms/GetMessage';
import GetMessages from './Forms/GetMessages';

function App() {
  return (
    <React.Fragment>
      <Typography variant='h3'>
        PUT /credentials 
      </Typography>
      <Typography variant='caption'>
        These fields will be used to sign and authenticate future requests. (Do not erase them)
      </Typography>
      <Crendentials/>
      <br/>
      <br/>
      <Typography variant='h3'>
        POST /message
      </Typography>
      <AddMessage/>
      <br/>
      <br/>
      <Typography variant='h3'>
        {'GET /message/<id>'}
      </Typography>
      <GetMessage/>
      <br/>
      <br/>
      <Typography variant='h3'>
        {'GET /messages/<tag>'}
      </Typography>
      <GetMessages/>
    </React.Fragment>
  )
}

export default App;
