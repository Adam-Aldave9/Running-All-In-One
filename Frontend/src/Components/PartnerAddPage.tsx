import { Link, useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { useEffect, useState } from "react";
import axios from "axios";

function PartnerAddPage() {
  interface SearchData {
    userId: string,
    username: string,
    name: string,
    location: string,
    availability: string
  }

  const params = useParams<{username: string, userid: string, search: string}>();
  
  const [searchData, setSearchData] = useState<SearchData[]>([{
    userId: "",
    username: "",
    name: "",
    location: "",
    availability: ""
  }])

  // change any type later when response query shape is known/resolved
  const [currentPartners, setCurrentPartners] = useState<{partnerName: string}[]>([{
      partnerName: ""
  }])

  const [noResultMessage, setNoResultMessage] = useState("");


  useEffect(() => {
    // get search results
    getSearchResults();
    getCurrentPartners();
  }, [])

  async function getSearchResults(): Promise<void> {
    // get search results
    try{
      const result = await axios.get(`http://localhost:80/userinformation/find/${params.search}`)
      if(result.data.length === 0) {
        setNoResultMessage("No Users found");
        return;
      }
      setNoResultMessage("");
      setSearchData(result.data);
    } catch (error) {
      console.error("error getting search results " + error);
    }
  }

  async function addPartner(username: string): Promise<void> {
    const payload = {userID: params.userid, partnerName: username}
    await axios.post("http://localhost:80/partners/add/", payload);
  }

  async function getCurrentPartners(): Promise<void> {
      const result = await axios.get(`http://localhost:80/partners/ownedpartners/name/${params.userid}`)
      setCurrentPartners(result.data);
  }

  function Document(props: SearchData): JSX.Element {
      let display = "block";
      let filterAlreadyPartner = currentPartners.filter((partner: {partnerName: string}) => partner.partnerName === props.username);
      if(filterAlreadyPartner.length != 0) {
        display = "hidden";
      }
      return (
        <div className="grid grid-cols-5 md:grid-cols-5 gap-4 py-7 border-b border-gray-300">
              <div className="font-semibold text-gray-950">Username: {props.username}</div>
              <div>Name: {props.name}</div>
              <div>Location: {props.location}</div>
              <div>Availability: {props.availability}</div>
              <button onClick={(e) => addPartner(props.username)} className={"bg-green-500 text-white rounded-md py-2 px-3 text-sm font-medium "+display}>Add Partner</button>
          </div>
      )
  }

  function displayResults(){
      // return mapping of sessions via Document
      return searchData.map((search: SearchData) => {
        return <Document userId={search.userId} username={search.username} name={search.name} location={search.location} availability={search.availability}/>
      })
  }

  return (
    <section className = "min-h-screen from-purple-200 via-purple-300 to-purple-500 bg-gradient-to-br">
        <Navbar username={params.username} userid={params.userid}/>
        <section className="p-4">
            Search Results For: {params.search}
            {/**Map search results */}
            {displayResults()}
            {/**if no results, display no results message */}
            <div>{noResultMessage}</div>
        </section>
    </section>
  );
}

export default PartnerAddPage;