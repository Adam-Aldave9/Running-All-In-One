import { useEffect, useState } from "react";
import Navbar from "./Navbar";
import { useParams } from "react-router-dom";
import axios from "axios";

function EditSession(): JSX.Element {
    interface Data {
        date: string,
        time: string,
        location: string
    }

    const params = useParams<{username: string, sessionId: string}>();
    const [data, setData] = useState<Data>({
        date: "",
        time: "",
        location: ""
    });
    const [userID, setUserID] = useState("");

    useEffect(() => {
        loadData();
        getUserId();
    }, [])

    async function loadData(): Promise<void> {
        try{
            const response = await axios.get(`http://localhost:80/sessions/${params.sessionId}`);
            setData({
                date: response.data.date,
                time: response.data.time,
                location: response.data.location
            })
        } catch (error) {
            console.log(error);
        }
    }

    function onChangeDate(e:  React.ChangeEvent<HTMLInputElement>): void {
        setData({
            ...data,
            date: e.target.value
        });
    }

    function onChangeTime(e:  React.ChangeEvent<HTMLInputElement>): void {
        setData({
            ...data,
            time: e.target.value
        });
    }

    function onChangeLocation(e:  React.ChangeEvent<HTMLInputElement>): void {
        setData({
            ...data,
            location: e.target.value
        });
    }

    async function onChange(): Promise<void> {
        //submit change data and redirect page
        try{
            await axios.put(`http://localhost:80/sessions/${params.sessionId}`, data)
            window.location.assign(`/viewsessions/${params.username}`)
        } catch (error) {
            console.log(error);
        }
    }

    async function getUserId(): Promise<void> {
        const response = await axios.get(`http://localhost:80/userinformation/find/${params.username}`)
        setUserID(response.data.userId)
    }

    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <Navbar username={params.username} userid={userID}></Navbar>
            <div className="mt-32">
                <div className="relative py-3 sm:w-96 mx-auto text-center">
                    <span className="text-2xl font-light ">Edit Session</span>
                    <div className="mt-4 bg-white shadow-md rounded-lg text-left">
                        <div className="px-8 py-6 ">
                            <label className="block font-semibold">Date</label>
                            <input type="text" value={data.date} onChange={onChangeDate} placeholder="yy/mm/dd" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/>
                            <label className="block font-semibold">Time</label>
                            <input type="text" value={data.time} onChange={onChangeTime} placeholder="00:00 - 23:59" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/>
                            <label className="block font-semibold">Location</label>
                            <input type="text" value={data.location} onChange={onChangeLocation} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/>
                            <div className="flex justify-between items-baseline"><button onClick={onChange} className="mt-4 bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Change</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default EditSession;