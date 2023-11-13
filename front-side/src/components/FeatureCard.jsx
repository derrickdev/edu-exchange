import React from "react";
import Icon from "./Icon";

const FeatureCard = ({ icon, title, description, bg, color }) => {
  return (
    <div className="flex flex-col sm:flex-row justify-center items-center gap-6 flex-1 min-w-[300px] max-w-[400px]">
      <div className={`${bg} ${color} w-12 h-12 rounded-[50%] flex justify-center items-center`}>
        <Icon name={icon} />
      </div>
      <div className="flex flex-col gap-1 sm:w-[80%]">
        <h4 className="text-slate-700 font-semibold text-lg">{title}</h4>
        <p className="text-slate-500 text-sm">{description}</p>
      </div>
    </div>
  );
};

export default FeatureCard;
