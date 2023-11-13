import React from "react";
import { dm_sans } from "@/utils/fonts";

const Counter = ({count, text}) => {
  return (
    <div className={`flex gap-2 items-center ${dm_sans.className}`}>
      <p className={`text-2xl font-bold ${dm_sans.className}`}>{count}</p>
      <p className={`text-xs text-light-primary ${dm_sans.className}`} dangerouslySetInnerHTML={{__html: text}}></p>
    </div>
  );
};

export default Counter;
