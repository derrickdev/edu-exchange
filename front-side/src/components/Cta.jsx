import React from "react";
import Button from "./Button";

const Cta = () => {
  return (
    <div className="flex flex-col justify-center items-center gap-8 py-16 sm:py-20">
      <p className="text-4xl sm:text-5xl leading-[3rem] sm:leading-[3.8rem] font-bold text-black-primary text-center sm:w-[70%]">
        Join EduExchange Today and Be Part of the Learning Revolution!
      </p>
      <Button
        text="Start chatting now"
        className="cursor-pointer py-3 px-6 rounded-md text-white bg-custom-orange text-sm flex items-center gap-2"
        to="/chat"
      />
    </div>
  );
};

export default Cta;
