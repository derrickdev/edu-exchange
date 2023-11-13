import React from "react";

const Testimonial = ({ title, content, author, authorRole }) => {
  return (
    <div className="flex-1 flex flex-col items-center justify-center gap-2">
      <div className="flex flex-col items-center gap-2 text-center p-6 rounded-lg shadow-sm bg-white">
        <p className="text-purple-500 font-semibold">{title}</p>
        <p className="">{content}</p>
      </div>
      <div>
        <p className="text-white">{author}</p>
        {authorRole ? <p className="text-white">{authorRole}</p> : null}
      </div>
    </div>
  );
};

export default Testimonial;
