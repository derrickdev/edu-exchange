import React, { useEffect, useState } from 'react';

import Select from 'react-select';
import makeAnimated from 'react-select/animated';
import axiosRequest from '@/utils/axiosRequest';

const animatedComponents = makeAnimated();

const formatOptionLabel = ({ name}) => (
    <div style={{ flex: "10" }}>
      <h5>{name}</h5>
    </div>
  );

export default function TopicsList({setSelectedTopics}) {
    const [topics, setTopics] = useState(null)

    useEffect(()=>{
        axiosRequest.get("/topics").then(res => {
            if(res.status === 200){
                console.log(res.data)
                setTopics(res.data?.content)
            }
        }).catch(err => console.log(err))
    }, [])

    const handleChange = (val) => {
        const topics = val.map((v) => v.name);
        setSelectedTopics(topics)
    }
    

  return (
    <Select
      closeMenuOnSelect={false}
      components={animatedComponents}
      isMulti
      options={topics}
      getOptionLabel={option =>`${option.name}`}
      getOptionValue={option =>`${option.name}`}
      onChange={handleChange}
      className='z-20'
    />
  );
}