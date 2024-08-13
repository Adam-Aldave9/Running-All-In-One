import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignIn from './Components/SignIn';
import SignUp from './Components/SignUp';
import ViewSessions from './Components/ViewSessions';
import CreateSession from './Components/CreateSession';
import EditSession from './Components/EditSession';
import UserProfile from './Components/UserProfile';
import EditUserProfile from './Components/EditUserProfile';
import ViewAssociated from './Components/ViewAssociated';
import CreateGroup from './Components/CreateGroup';
import EditGroup from './Components/EditGroup';
import Search from './Components/Search';
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          {/**Remember, screens arent tied to services. you can pull data from multiple services onto a single page*/}
          <Route path="/" element={<SignIn></SignIn>} />
          <Route path="/signup" element={<SignUp></SignUp>} />
          <Route path="/viewsessions/:username" element={<ViewSessions></ViewSessions>}/>
          <Route path="/createsession/:username/:id" element={<CreateSession></CreateSession>} />
          <Route path="/editsession/:username/:id" element={<EditSession></EditSession>} />
          <Route path="/userprofile/:username/:id" element={<UserProfile></UserProfile>} />
          <Route path="/edituserprofile/:username/:id" element={<EditUserProfile></EditUserProfile>} />
          <Route path="/viewassociated/:username/:id" element={<ViewAssociated></ViewAssociated>} />
          <Route path="/creategroup/:username/:id" element={<CreateGroup></CreateGroup>} />{/**may wipe out groups */}
          <Route path="/editgroup/:username/:id" element={<EditGroup></EditGroup>} />
          <Route path="/search/:username/:id" element={<Search></Search>} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
