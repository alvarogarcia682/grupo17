import {useState, useMemo} from 'react';

export function useSearch(items, searchKey) {
    const [query, setQuery] = useState("");

    const filteredItems = useMemo(() => {
        if (!query) return items;

        return items.filter((item) =>
            item[searchKey].toLowerCase().includes(query.toLowerCase())
        );
    }, [items, searchKey, query]);

    return {query, setQuery, filteredItems};
}