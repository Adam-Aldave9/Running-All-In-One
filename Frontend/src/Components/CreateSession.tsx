import { useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { useEffect, useState } from "react";
import axios from "axios";

function CreateSession(): JSX.Element {
    interface ParticipantData {
        partnerOne: string | undefined,
        partnerTwo: string,
        sessionId: string
    }
    interface Data {
        date: string,
        time: string,
        location: string
    }

    const params = useParams<{username: string, userid: string}>();
    const [data, setData] = useState<Data>({
        date: "",
        time: "",
        location: ""
    });

    const [participantData, setParticipantData] = useState<ParticipantData>({
        partnerOne: "",
        partnerTwo: "",
        sessionId: ""
    })

    useEffect(() => {
        setParticipantData({
            ...participantData,
            partnerOne: params.userid
        })
    }, [])

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

    function onChangeRunningPartner(e:  React.ChangeEvent<HTMLInputElement>): void {
        setParticipantData({
            ...participantData,
            partnerOne: e.target.value
        });
    }

    async function onCreateSession(): Promise<void> {
        //send request to sessions and session participants table
        //query sessionId or are we able to generate one and send with payload?
        try{
            const responseSessionsAdd = await axios.post("http://localhost:80/sessions/add", data)
            setParticipantData({
                ...participantData,
                sessionId: responseSessionsAdd.data.sessionId
            })
            await axios.post("http://localhost:80/sessionparticipants/add", participantData)
            window.location.assign(`/viewsessions/${params.username}`)
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <Navbar username={params.username} userid={params.userid}></Navbar>
            <div className="mt-32">
                <div className="relative py-3 sm:w-96 mx-auto text-center">
                    <span className="text-2xl font-light ">Create Session</span>
                    <div className="mt-4 bg-white shadow-md rounded-lg text-left">
                        <div className="px-8 py-6 ">
                            <label className="block font-semibold">Date</label>
                            <input type="text" onChange={onChangeDate} placeholder="yy/mm/dd" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> 
                            <label className="block font-semibold">Time</label>
                            <input type="text" onChange={onChangeTime} placeholder="00:00 - 23:59" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/>
                            <label className="block font-semibold">Location</label>
                            <input type="text" onChange={onChangeLocation}  className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/>
                            <label className="block font-semibold">Running Partner</label>
                            <input type="text" onChange={onChangeRunningPartner} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/>                    
                            <div className="flex justify-between items-baseline"><button onClick={onCreateSession} className="mt-4 bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Create</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default CreateSession;