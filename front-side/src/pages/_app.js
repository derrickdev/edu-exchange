import React, { useEffect } from "react";
import "@/styles/globals.css";
import Layout from "@/components/layout";

export default function App({ Component, pageProps }) {
  const getLayout = Component.getLayout || ((page) => <Layout>{page}</Layout>);

  return getLayout(<Component {...pageProps} />);
}


// INSERT INTO topic (name) VALUES
//   ('Topic 1'),
//   ('Topic 2'),
//   ('Topic 3'),
//   ('Topic 4'),
//   ('Topic 5'),
//   ('Topic 6'),
//   ('Topic 7'),
//   ('Topic 8'),
//   ('Topic 9'),
//   ('Topic 10');

// INSERT INTO post (content, created_at, user_id, title) VALUES
//   ('Post 12 Content', NOW(),  1,'When are we going to Mars');
// INSERT INTO post (content, created_at, user_id,  title) VALUES
//   ('Post 22 Content', NOW(), 2, 'How to implement spring security in a Spring boot project');
