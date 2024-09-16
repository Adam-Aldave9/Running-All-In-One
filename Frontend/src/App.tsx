import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SignIn from './Components/SignIn';
import SignUp from './Components/SignUp';
import ViewSessions from './Components/ViewSessions';
import CreateSession from './Components/CreateSession';
import EditSession from './Components/EditSession';
import UserProfile from './Components/UserProfile';
import EditUserProfile from './Components/EditUserProfile';
import PartnerAddPage from './Components/PartnerAddPage';
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
          <Route path="/createsession/:username/:userid" element={<CreateSession></CreateSession>} />
          <Route path="/editsession/:username/:sessionid" element={<EditSession></EditSession>} />
          <Route path="/userprofile/:username/:userid" element={<UserProfile></UserProfile>} />
          <Route path="/edituserprofile/:username/:userid" element={<EditUserProfile></EditUserProfile>} />
          <Route path="/partneradd/:username/:userid/:search" element={<PartnerAddPage></PartnerAddPage>} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
