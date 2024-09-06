import { Link } from "react-router-dom";

function Navbar() {
    return(
        <header className="bg-white">
            <div className="mx-auto max-w-7xl px-2 sm:px-4 lg:divide-y lg:divide-teal-700 lg:px-8">
                <div className="relative flex h-16 justify-between">
                    <div className="relative z-10 flex px-2 lg:px-0">
                        <div className="flex flex-shrink-0 items-center">
                        <h1 className="text-orange-700 font-bold text-2xl">Running Community</h1>
                        </div>
                    </div>
                    <div className="relative z-0 flex flex-1 items-center justify-center px-2 sm:absolute sm:inset-0">
                        <div className="w-full sm:max-w-xs">
                        <label  className="sr-only">Search</label>
                        <div className="relative">
                            <div className="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-3">
                            <svg className="h-5 w-5 text-gray-400" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                                <path fill-rule="evenodd" d="M9 3.5a5.5 5.5 0 100 11 5.5 5.5 0 000-11zM2 9a7 7 0 1112.452 4.391l3.328 3.329a.75.75 0 11-1.06 1.06l-3.329-3.328A7 7 0 012 9z" clip-rule="evenodd" />
                            </svg>
                            </div>
                            <input id="search" name="search" className="block w-full rounded-md border-0 bg-teal-500 py-1.5 pl-10 pr-3 text-yellow-300 placeholder:text-yellow-400 focus:bg-white focus:text-gray-900 focus:ring-0 focus:placeholder:text-gray-500 sm:text-sm sm:leading-6" placeholder="Search" type="search"/>
                        </div>
                        </div>
                    </div>
                </div>
                <nav className="hidden lg:flex lg:space-x-8 lg:py-2" aria-label="Global">
                    <Link to="/viewsessions/username" className="bg-gray-900 text-white inline-flex items-center rounded-md py-2 px-3 text-sm font-medium" aria-current="page">(Home) View Sessions</Link>
                    <Link to="/createsession/username/id" className="text-cyan-500 bg-yellow-400 hover:bg-cyan-500 hover:text-yellow-400 inline-flex items-center rounded-md py-2 px-3 text-sm font-medium">Create Session</Link>
                    <Link to="/userprofile/username/id" className="text-cyan-500 bg-yellow-400 hover:bg-cyan-500 hover:text-yellow-400 inline-flex items-center rounded-md py-2 px-3 text-sm font-medium">User Profile</Link>
                </nav>
            </div>

            {/* Mobile menu, show/hide based on menu state. */}
            <nav className="lg:hidden" aria-label="Global" id="mobile-menu">
                <div className="space-y-1 px-2 pb-3 pt-2">
                    <Link to="/viewsessions/username" className="bg-gray-900 text-white block rounded-md py-2 px-3 text-base font-medium" aria-current="page">(Home) View Sessions</Link>
                    <Link to="/createsession/username/id" className="text-gray-300 hover:bg-gray-700 hover:text-white block rounded-md py-2 px-3 text-base font-medium">Create Session</Link>
                    <Link to="/userprofile/username/id" className="text-gray-300 hover:bg-gray-700 hover:text-white block rounded-md py-2 px-3 text-base font-medium">User Profile</Link>
                </div>
            </nav>
        </header>
    )
}

export default Navbar;