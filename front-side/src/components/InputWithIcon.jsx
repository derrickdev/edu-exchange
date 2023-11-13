import React from "react";
import Icon from "./Icon";

const InputWithIcon = ({
  placeholder,
  startIcon,
  size,
  classnames,
  handleIconClick,
  endIcon,
  endIconClick,
  ...other
}) => {
  return (
    <div className={`relative h-10 ${classnames}`}>
      <div className="absolute top-2/4 left-1 grid h-5 w-5 -translate-y-2/4 place-items-center text-blue-gray-500">
        <Icon
          name={startIcon}
          size={size}
          onClick={handleIconClick || null}
          className="cursor-pointer"
        />
      </div>
      <input
        className="h-full w-full bg-transparent pl-8 py-2.5 font-sans text-sm font-normal outline-none"
        placeholder={placeholder}
        {...other}
      />
      {endIcon ? (
        <div className="absolute top-2/4 right-1 grid h-5 w-5 -translate-y-2/4 place-items-center text-blue-gray-500">
          <Icon
            name={endIcon}
            size={size}
            onClick={endIconClick || null}
            className="cursor-pointer"
          />
        </div>
      ) : null}
    </div>
  );
};

export default InputWithIcon;
