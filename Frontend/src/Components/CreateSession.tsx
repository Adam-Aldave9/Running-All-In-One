import { useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { useEffect, useState } from "react";

function CreateSession() {
    interface participantData {
        partnerOne: string | undefined
        partnerTwo: string,
        sessionId: string
    }
    interface data {
        date: string,
        time: string,
        location: string
    }

    const params = useParams<{username: string, userid: string}>();
    const [data, setData] = useState<data>({
        date: "",
        time: "",
        location: ""
    });

    const [participantData, setParticipantData] = useState<participantData>({
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

    function onChangeDate(e:  React.ChangeEvent<HTMLInputElement>) {
        setData({
            ...data,
            date: e.target.value
        });
    }

    function onChangeTime(e:  React.ChangeEvent<HTMLInputElement>) {
        setData({
            ...data,
            time: e.target.value
        });
    }

    function onChangeLocation(e:  React.ChangeEvent<HTMLInputElement>) {
        setData({
            ...data,
            location: e.target.value
        });
    }

    function onChangeRunningPartner(e:  React.ChangeEvent<HTMLInputElement>) {
        setParticipantData({
            ...participantData,
            partnerOne: e.target.value
        });
    }

    async function onCreateSession(e:  React.MouseEvent<HTMLButtonElement>) {
        //send request to sessions and session participants table
        //query sessionId or are we able to generate one and send with payload?
    }

    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <Navbar />
            <div className="mt-32">
                <div className="relative py-3 sm:w-96 mx-auto text-center">
                    <span className="text-2xl font-light ">Create Session</span>
                    <div className="mt-4 bg-white shadow-md rounded-lg text-left">
                        <div className="px-8 py-6 ">
                            <label className="block font-semibold">Date</label>
                            <input type="text" onChange={onChangeDate} placeholder="yy/mm/dd" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                            <label className="block font-semibold">Time</label>
                            <input type="text" onChange={onChangeTime} placeholder="00:00 - 23:59" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                            <label className="block font-semibold">Location</label>
                            <input type="text" onChange={onChangeLocation}  className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                            <label className="block font-semibold">Running Partner</label>
                            <input type="text" onChange={onChangeRunningPartner} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*Replace with dropdown list of partners*/}                      
                            <div className="flex justify-between items-baseline"><button onClick={onCreateSession} className="mt-4 bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Create</button></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default CreateSession;