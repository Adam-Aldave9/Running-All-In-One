import { useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { useState } from "react";

function EditGroup() {
    // add navbar for back
    interface data {
        date: string,
        time: string,
        location: string
    }
    const params = useParams<{username: string, userid: string, groupId: string}>();
    const [data, setData] = useState<data>({
        date: "",
        time: "",
        location: ""
    });

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

    async function onChange(e: React.MouseEvent<HTMLButtonElement>) {
        //POST request info to backend and redirect page
    }

    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <Navbar />
            <div className="mt-32">                
                <div className="relative py-3 sm:w-96 mx-auto text-center">
                    <span className="text-2xl font-light ">Edit a Group</span>
                    <div className="mt-4 bg-white shadow-md rounded-lg text-left">
                    <div className="h-2 bg-purple-400 rounded-t-md"></div>
                    <div className="px-8 py-6 ">
                        <label className="block font-semibold"> Group Name </label>
                        <input type="text" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <label className="block font-semibold">Date</label>
                            <input type="text" onChange={onChangeDate} placeholder="yy/mm/dd" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                            <label className="block font-semibold">Time</label>
                            <input type="text" onChange={onChangeTime} placeholder="00:00 - 23:59" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                            <label className="block font-semibold">Location</label>
                            <input type="text" onChange={onChangeLocation} className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <div className="flex justify-between items-baseline">
                            <button onClick={onChange} className="mt-4 bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Change</button>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}


export default EditGroup;