import React from "react";

const InputWithLabel = ({label, htmlFor, type, ...other}) => {
  return (
    <div>
      <div className="mt-2">
        <label
          htmlFor={htmlFor}
          className="block mb-2 text-sm font-medium text-gray-900 "
        >
          {label}
        </label>
        <input
          type={type}
          {...other}
        />
      </div>
    </div>
  );
};

export default InputWithLabel;
