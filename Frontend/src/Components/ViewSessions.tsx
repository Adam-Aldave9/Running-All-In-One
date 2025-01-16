import { Link, useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { useEffect, useState } from "react";
import axios from "axios";

function ViewSessions(): JSX.Element {
    interface SessionTimeSpanInput{
        year: string,
        month: string
    }
    interface SessionData{
        sessionId: string,
        date: string,
        time: string,
        location: string,
        partnerOne: string,
        partnerTwo: string
    }
    const params = useParams<{username: string}>();
    const [sessionInput, setSessionInput] = useState<SessionTimeSpanInput>({
        year: "",
        month: ""
    })
    const [sessionData, setSessionData] = useState<SessionData[]>([{
        partnerOne: "",
        partnerTwo: "", 
        date: "",
        location: "",
        time: "",
        sessionId: ""
    }]);
    const [userID, setUserID] = useState("");

    useEffect(() => {
        // get all user sessions
        loadSessionsData();
        getUserId();
    }, [])

    async function loadSessionsData(): Promise<void> {
        // get sessions for the month and year selected
        try{
            const response = await axios.get(`http://localhost:80/sessionparticipants/sessionsjoin/${params.username}`)
            if(response.data.length === 0) {
                console.log("No sessions found")
                return;
            }
            setSessionData(response.data);
        } catch (error) {
            console.error("error loading sessions " + error)
        }
    }

    function getSessions(): JSX.Element[] {
        // return mapped documents for each session
        const monthMap: { [key: string]: string } = {
            "January": "01",
            "February": "02",
            "March": "03",
            "April": "04",
            "May": "05",
            "June": "06",
            "July": "07",
            "August": "08",
            "September": "09",
            "October": "10",
            "November": "11",
            "December": "12"
        };

        let sorted = sessionData.filter((session) => {
            let date = session.date.split("/");
            let year = date[0]
            let month = date[1]
            return sessionInput.year === year && monthMap[sessionInput.month] === month
        })
        return sorted.map((session: SessionData) => {
            return <Document partnerOne={session.partnerOne} partnerTwo={session.partnerTwo} time={session.time} location={session.location} sessionId={session.sessionId}/>
        })
    }

    function DisplaySessions(): JSX.Element {
        // display sessions based on year and month selected in ChooseMonth
        return (
            <div>
                {getSessions()}
            </div>
        )
    }

    function Document(props: {partnerOne: string, partnerTwo: string, time: string, location: string, sessionId: string}): JSX.Element {
        let partner: string
        if(params.username === props.partnerOne) partner = props.partnerTwo;
        else partner = props.partnerOne;
        return (
            <div className="grid grid-cols-4 md:grid-cols-4 gap-4 py-7 border-b border-gray-300">
              <div className="font-semibold text-gray-950">Partner: {partner}</div>
              <div>Time: {props.time}</div>
              <div>Location: {props.location}</div>
              <div><Link to={`/editsession/${params.username}/${props.sessionId}`} className="bg-purple-500 text-white py-2 px-6 rounded-md hover:bg-purple-600 ">Edit</Link></div>
          </div>
        )
    }

    function onChangeMonth(e: React.ChangeEvent<HTMLSelectElement>): void {
        e.preventDefault()
        setSessionInput({
            ...sessionInput,
            month: e.target.value
        });
    }

    function onChangeYear(e: React.ChangeEvent<HTMLInputElement>): void {
        e.preventDefault()
        setSessionInput({
            ...sessionInput,
            year: e.target.value
        });
    }

    async function getUserId(): Promise<void> {
        const response = await axios.get(`http://localhost:80/userinformation/find/${params.username}`)
        setUserID(response.data.userId)
    }

    return (
        <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
            <div> 
                <Navbar username={params.username} userid={userID}></Navbar>
                <section className="p-4">
                    <div className="mb-2 text-lg font-semibold">Choose Year and Month to View Sessions</div>
                    <input 
                        type="text" 
                        value={sessionInput.year}
                        onChange={onChangeYear} 
                        className="mb-4 p-2 border border-gray-300 rounded"
                        placeholder="Enter Year"
                    />
                    <select 
                        className="p-2 border border-gray-300 rounded"
                        value={sessionInput.month}
                        onChange={onChangeMonth}
                    >
                        
                        <option value="January">January</option>
                        <option value="February">February</option>
                        <option value="March">March</option>
                        <option value="April">April</option>
                        <option value="May">May</option>
                        <option value="June">June</option>
                        <option value="July">July</option>
                        <option value="August">August</option>
                        <option value="September">September</option>
                        <option value="October">October</option>
                        <option value="November">November</option>
                        <option value="December">December</option>
                    </select>
                </section>
                <DisplaySessions></DisplaySessions>
            </div>
        </section>
    )
}

export default ViewSessions;
