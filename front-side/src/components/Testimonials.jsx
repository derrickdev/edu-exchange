import React from "react";
import Testimonial from "./Testimonial";

const data = [
  {
    title: "Incredible Experience",
    content:
      "We had an incredible experience working with Mixland and were impressed they made such a big difference in only three weeks. Our team is so grateful for the wonderful improvements they made and their ability to get familiar with the concept so quickly.",
    author: "Wade Warren",
    authorRole: "Teacher",
  },
  {
    title: "Dependable, Responsive, Professional",
    content:
      "Fermin Apps has collaborated with Mixland team for several projects such as Photo Sharing Apps and Custom Social Networking Apps. The experience has been pleasant, professional and exceeding our expectations. The team is always thinking beyond.",
    author: "Esther Howard",
    authorRole: "Student",
  },
];

const Testimonials = () => {
  return (
    <div className="flex flex-wrap gap-4 py-40 px-40 bg-custom-orange">
      {data.map((e, i) => (
        <Testimonial
          key={`testimonial-${i}`}
          title={e?.title}
          author={e?.author}
          content={e?.content}
          authorRole={e?.authorRole}
        />
      ))}
    </div>
  );
};

export default Testimonials;
