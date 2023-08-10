package com.test.machinetest.ui.adapters;

import java.util.List;

interface AdapterUtil<itemType> {

    itemType getItemAt(int position);

    void addAt(int position, itemType item);

    void addItem(itemType item);

    void setList(List<itemType> items);

    void addList(List<itemType> items);

    void removeAt(int position);

}
