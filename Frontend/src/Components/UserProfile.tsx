import { Link, useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { useEffect, useState } from "react";
import axios from "axios";
import { send } from "process";

function UserProfile(): JSX.Element {
  // This component will display the user's profile information
  // The user will be able to edit their profile information
  // Reference the User Information table in UPAM for necessary data
  // send login data to history table in auth
  interface UserInformation {
    user_id: string,
    username: string,
    name: string,
    age: number,
    location: string,
    experience: string,
    availability: string
  }

  interface Partners { 
    partnerName: string
    partnerId: string,
  }

  const params = useParams<{username: string, userid: string}>();
  const [userInformation, setUserInformation] = useState<UserInformation>({
    user_id: "",
    username: "",
    name: "",
    age: 0,
    location: "",
    experience: "",
    availability: ""
  });
  const [partners, setPartners] = useState<Partners[]>([{partnerId: "tempid", partnerName: "temp name"}, {partnerId: "tempid", partnerName: "temp name"}]);
  const [isProfileLoaded, setIsProfileLoaded] = useState(false)
  const [isPartnersLoaded, setIsPartnersLoaded] = useState(false)

  useEffect(() => {
    //get current user profile data
    if(!isProfileLoaded) onLoadProfile();
    if(!isPartnersLoaded) onLoadPartners();
  }, [isProfileLoaded]);

  async function onLoadProfile(): Promise<void> {  
    try{
      const response = await axios.get(`http://localhost:80/userinformation/${params.userid}`);
      console.log(response.data);
      setUserInformation(response.data);
      setIsProfileLoaded(true);
    } catch (error) {
      console.error("error olprof " +error);
    }
  }

  async function onLoadPartners(): Promise<void> {
    try{
        const response = await axios.get(`http://localhost:80/partners/ownedpartners/${params.userid}`);
        console.log(response.data);
        setPartners(response.data);
        setIsPartnersLoaded(true);
    } catch (error) {
        console.error("error olp " +error);
    }
  }

  async function onClickRemove(partnerName: string): Promise<void> {
    //remove partner from partner table
    await axios.delete(`http://localhost:80/partners/${params.userid}/${partnerName}`);
  }

  function Document(props: Partners): JSX.Element {
      return(
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
              <div className="font-semibold text-gray-950">{props.partnerName}</div>
              <div className="md:col-span-2 text-gray-700">
                  <button onClick={(e) => onClickRemove(props.partnerName)} className="mt-4 p-1 inline-block w-auto h-auto cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white">Remove</button>
              </div>
          </div>
      )
  }

  function listPartners(): JSX.Element[] {
      return partners.map((partner: Partners) => {
          return <Document partnerName={partner.partnerName} partnerId={partner.partnerId}/>
      })
  }

  return (
      <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
        <Navbar username={params.username} userid={params.userid}></Navbar>
        {/* Container */}
        <div className="mx-auto w-full max-w-7xl px-5 py-10 md:px-10 ">
          {/* Title */}
          <h4 className="text-xl md:text-3xl font-bold mb-2 text-gray-950 ">Profile Information</h4>
          <p className="text-gray-700 mb-10">Personal details and runner details</p>
          <Link to={`/edituserprofile/"+${params.username}+"/"+${params.userid}`} className="mt-4 inline-block w-1/5 cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white">Edit Profile</Link>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300" >
            <div className="font-semibold text-gray-950">Username</div>
            <div className="md:col-span-2 text-gray-700">{userInformation.username}</div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Name</div>
            <div className="md:col-span-2 text-gray-700">
              {userInformation.name}
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Age</div>
            <div className="md:col-span-2 text-gray-700">a</div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Location</div>
            <div className="md:col-span-2 text-gray-700">
            {userInformation.location}
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7">
            <div className="font-semibold text-gray-950">Experience</div>
            <div className="md:col-span-2 text-gray-700">
              {userInformation.experience}
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
              <div className="font-semibold text-gray-950">Availability</div>
              <div className="md:col-span-2 text-gray-700">{userInformation.availability}{/**map availabilities here via function */}</div>
          </div>
        </div>
        <h1 className="clear-right text-2xl mx-auto w-1/3 max-w-7xl px-5 py-10 md:px-10 font-bold">Partners</h1>
        <div>{/**function call for document mapping of partners here. Each mapping has a remove button*/}
            {listPartners()}
        </div>
      </section>
  );
}

export default UserProfile;