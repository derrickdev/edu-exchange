import React from "react";

const CustomBadge = ({ content, bgColor, textColor }) => {
  return (
    <div className={`rounded-sm p-2 ${bgColor} cursor-pointer hover:opacity-80`}>
      <span className={`text-sm font-semibold ${textColor}`}>{content}</span>
    </div>
  );
};

export default CustomBadge;