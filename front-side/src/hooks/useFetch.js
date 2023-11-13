import { useState, useEffect } from "react";

export const useFetch = (url, method, body) => {
  const [isLoading, setIsLoading] = useState(false);
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);

  const headers = {}
  const token = localStorage.getItem("app_access_token");

  if(token){
    headers["Authorization"]= `Bearer ${token}`
  }

  useEffect(() => {
    setIsLoading(true);
    const fetchData = async () => {
      try {
        const response = await fetch(url, {
          headers: headers,
          method: method,
          body: body
        });
        const data = await response?.data;

        setData(data);
        setIsLoading(false);
      } catch (error) {
        setError(error);
        setIsLoading(false);
      }
    };

    fetchData();
  }, [url]);

  return { isLoading, data, error };
};
