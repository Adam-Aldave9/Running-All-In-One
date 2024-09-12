import { useEffect, useState } from "react";
import Navbar from "./Navbar";
import { useParams } from "react-router-dom";
import axios from "axios";

function EditUserProfile(): JSX.Element {
    interface UserInformation {
        username: string,
        name: string,
        age: number,
        location: string,
        experience: string,
        availability: string
    }

    const params = useParams<{username: string, userid: string}>();
    const [userInformation, setUserInformation] = useState<UserInformation>({
        username: "",
        name: "",
        age: 0,
        location: "",
        experience: "",
        availability: ""
    });

    const [isDocLoaded, setIsDocLoaded] = useState(false)

    useEffect(() => {
        //get current user profile data
        if(isDocLoaded === false) onLoad();
    }, [isDocLoaded]);

    async function onLoad(): Promise<void> {
        const response = await axios.get(`http://localhost:80/userinformation/${params.userid}`);
        setUserInformation(response.data);
        setIsDocLoaded(true);
    }

    function onChangeUsername(e: React.ChangeEvent<HTMLInputElement>): void {
        setUserInformation({
            ...userInformation,
            username: e.target.value
        });
    }

    function onChangeName(e: React.ChangeEvent<HTMLInputElement>): void {
        setUserInformation({
            ...userInformation,
            name: e.target.value
        });
    }

    function onChangeAge(e: React.ChangeEvent<HTMLInputElement>): void {
        setUserInformation({
            ...userInformation,
            age: parseInt(e.target.value)
        });
    }

    function onChangeLocation(e: React.ChangeEvent<HTMLInputElement>): void {
        setUserInformation({
            ...userInformation,
            location: e.target.value
        });
    }

    function onChangeExperience(e: React.ChangeEvent<HTMLInputElement>): void {
        setUserInformation({
            ...userInformation,
            experience: e.target.value
        });
    }

    function onChangeAvailability(e: React.ChangeEvent<HTMLInputElement>): void {
        setUserInformation({
            ...userInformation,
            availability: e.target.value
        });
    }

    async function onChange(e: React.MouseEvent<HTMLButtonElement>): Promise<void> {
        //PUT request info to backend and redirect page
        await axios.put(`http://localhost:80/userinformation/${params.userid}`, userInformation);
        window.location.assign("http://localhost:3000/userprofile/" + params.username + "/" + params.userid);
    }


    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <Navbar />
            <div className="mt-8">
                <div className="relative py-3 sm:w-96 mx-auto text-center">
                    <span className="text-2xl font-light ">Edit Profile</span>
                    <div className="mt-4 bg-white shadow-md rounded-lg text-left">
                    <div className="h-2 bg-purple-400 rounded-t-md"></div>
                    <div className="px-8 py-6 ">
                        <label className="block font-semibold">Username</label>
                        <input type="text" value={userInformation.username} placeholder={userInformation.username} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md" onChange={onChangeUsername}/>
                        <label className="block font-semibold">Name</label>
                        <input type="text" value={userInformation.name} placeholder={userInformation.name} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md" onChange={onChangeName}/>
                        <label className="block font-semibold">Age</label>
                        <input type="text" value={userInformation.age.toString()} placeholder={userInformation.age.toString()} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md" onChange={onChangeAge}/>
                        <label className="block font-semibold">Location</label>
                        <input type="text" value={userInformation.location} placeholder={userInformation.location} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md" onChange={onChangeLocation}/>
                        <label className="block font-semibold">Experience</label>
                        <input type="text" value={userInformation.experience} placeholder={userInformation.experience} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md" onChange={onChangeExperience}/>
                        <label className="block font-semibold">Availability</label>
                        <input type="text" value={userInformation.availability} placeholder={userInformation.availability} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md" onChange={onChangeAvailability}/>
                        <div className="flex justify-between items-baseline">
                            <button onClick={onChange} className="mt-4 bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Change</button>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default EditUserProfile;