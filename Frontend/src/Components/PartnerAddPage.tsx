import { Link } from "react-router-dom";
import Navbar from "./Navbar";

function PartnerAddPage() {
  return (
    <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
        <Navbar />
        {/* Container */}
        <div className="mx-auto w-full max-w-7xl px-5 py-10 md:px-10 ">
          {/* Title */}
          <h4 className="text-xl md:text-3xl font-bold mb-2 text-gray-950 ">Insert Search Selected name here</h4>
          <button className="mt-4 inline-block w-1/5 cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white">Add as Partner</button>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Username</div>
            <div className="md:col-span-2 text-gray-700">u</div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Name</div>
            <div className="md:col-span-2 text-gray-700">
              n
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Age</div>
            <div className="md:col-span-2 text-gray-700">a</div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
            <div className="font-semibold text-gray-950">Location</div>
            <div className="md:col-span-2 text-gray-700">
            l
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7">
            <div className="font-semibold text-gray-950">Experience</div>
            <div className="md:col-span-2 text-gray-700">
              e
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4 py-7 border-b border-gray-300">
              <div className="font-semibold text-gray-950">Availability</div>
              <div className="md:col-span-2 text-gray-700">av{/**map availabilities here via function */}</div>
          </div>
        </div>
        <h1 className="clear-right text-2xl mx-auto w-1/3 max-w-7xl px-5 py-10 md:px-10 font-bold">Partners</h1>
        <div>{/**function call for document mapping of partners here. Each mapping has an edit button*/}</div>
        <h1 className="clear-right text-2xl mx-auto w-full max-w-7xl px-5 py-10 md:px-10 font-bold">Groups</h1>
        <div>{/**function call for document mapping of groups here. Each mapping has an edit button*/}</div>
      </section>
  );
}

export default PartnerAddPage;