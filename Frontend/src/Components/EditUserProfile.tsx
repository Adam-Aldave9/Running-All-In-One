import Navbar from "./Navbar";

function EditUserProfile() {
    //add navbar for back
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
                        <input type="text" placeholder="temp" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <label className="block font-semibold">Name</label>
                        <input type="text" placeholder="temp" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <label className="block font-semibold">Age</label>
                        <input type="text" placeholder="temp" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <label className="block font-semibold">Location</label>
                        <input type="text" placeholder="temp" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <label className="block font-semibold">Experience</label>
                        <input type="text" placeholder="temp" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <label className="block font-semibold">Availability</label>
                        <input type="text" placeholder="temp" className="border w-full h-5 px-3 py-5 mt-2 hover:outline-none focus:outline-none focus:ring-indigo-500 focus:ring-1 rounded-md"/> {/*placeholder will be function call for current group name */}
                        <div className="flex justify-between items-baseline">
                            <button type="submit" className="mt-4 bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Change</button>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default EditUserProfile;